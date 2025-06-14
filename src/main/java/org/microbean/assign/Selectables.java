/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2025 microBean™.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.microbean.assign;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.concurrent.ConcurrentHashMap;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Utility methods for working with {@link Selectable}s.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see Selectable
 */
public final class Selectables {

  private Selectables() {
    super();
  }
  
  /**
   * Returns a {@link Selectable} that caches its results.
   *
   * <p>The cache is unbounded.</p>
   *
   * @param <C> the criteria type
   *
   * @param <E> the element type
   *
   * @param selectable a {@link Selectable}; must not be {@code null}
   *
   * @return a non-{@code null} {@link Selectable}
   *
   * @exception NullPointerException if {@code selectable} is {@code null}
   *
   * @see #caching(Selectable, BiFunction)
   */
  public static <C, E> Selectable<C, E> caching(final Selectable<C, E> selectable) {
    final Map<C, List<E>> selectionCache = new ConcurrentHashMap<>();
    return Selectables.<C, E>caching(selectable, selectionCache::computeIfAbsent);
  }

  /**
   * Returns a {@link Selectable} that caches its results.
   *
   * @param <C> the criteria type
   *
   * @param <E> the element type
   *
   * @param selectable a {@link Selectable}; must not be {@code null}
   *
   * @param f a {@link BiFunction} that returns a cached result, computing it on demand via its supplied mapping {@link
   * Function} if necessary; must not be {@code null}; normally safe for concurrent use by multiple threads; often a
   * reference to the {@link ConcurrentHashMap#computeIfAbsent(Object, Function)} method
   *
   * @return a non-{@code null} {@link Selectable}
   *
   * @exception NullPointerException if {@code selectable} or {@code f} is {@code null}
   *
   * @see ConcurrentHashMap#computeIfAbsent(Object, Function)
   */
  public static <C, E> Selectable<C, E> caching(final Selectable<C, E> selectable,
                                                final BiFunction<? super C, Function<? super C, ? extends List<E>>, ? extends List<E>> f) {
    return c -> f.apply(c, selectable::select);
  }

  /**
   * Returns a {@link Selectable} whose {@link Selectable#select(Object)} method always returns an {@linkplain List#of()
   * empty, immutable <code>List</code>}.
   *
   * <p>This method is useful primarily for completeness and for testing pathological situations.</p>
   *
   * @param <C> the criteria type
   *
   * @param <E> the element type
   *
   * @return a non-{@code null} {@link Selectable}
   */
  public static final <C, E> Selectable<C, E> empty() {
    return Selectables::empty;
  }

  private static final <C, E> List<E> empty(final C ignored) {
    return List.of();
  }

  /**
   * Returns a {@link Selectable} using the supplied {@link Collection} as its elements, and the supplied {@link
   * BiPredicate} as its <em>selector</em>.
   *
   * <p>There is no guarantee that this method will return new {@link Selectable} instances.</p>
   *
   * <p>The {@link Selectable} instances returned by this method may or may not cache their selections.</p>
   *
   * <p>The selector must (indirectly) designate a sublist from the supplied {@link Collection} as mediated by the
   * supplied criteria. The selector must additionally be idempotent and must produce a determinate value when given the
   * same arguments.</p>
   *
   * <p>No validation of these semantics of the selector is performed.</p>
   *
   * @param <C> the criteria type
   *
   * @param <E> the element type
   *
   * @param collection a {@link Collection} of elements from which sublists may be selected; must not be {@code null}
   *
   * @param p the selector; must not be {@code null}
   *
   * @return a {@link Selectable}; never {@code null}
   *
   * @exception NullPointerException if either {@code collection} or {@code p} is {@code null}
   */
  @SuppressWarnings("unchecked")
  public static <C, E> Selectable<C, E> filtering(final Collection<? extends E> collection,
                                                  final BiPredicate<? super E, ? super C> p) {
    Objects.requireNonNull(p, "p");
    return collection.isEmpty() ? empty() : c -> (List<E>)collection.stream().filter(e -> p.test(e, c)).toList();
  }

  
}
