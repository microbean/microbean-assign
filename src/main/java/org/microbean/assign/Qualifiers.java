/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2025 microBean™.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.microbean.assign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.microbean.qualifier.NamedAttributeMap;

/**
 * A utility class for working with commonly-used <dfn>qualifiers</dfn>.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see NamedAttributeMap
 */
public final class Qualifiers {

  private static final NamedAttributeMap<String> QUALIFIER = new NamedAttributeMap<>("Qualifier");

  private static final List<NamedAttributeMap<String>> QUALIFIER_LIST = List.of(QUALIFIER);

  private static final NamedAttributeMap<?> ANY_QUALIFIER = new NamedAttributeMap<>("Any", Map.of(), Map.of(), QUALIFIER_LIST);

  private static final List<NamedAttributeMap<?>> ANY_QUALIFIERS = List.of(ANY_QUALIFIER);

  private static final NamedAttributeMap<?> DEFAULT_QUALIFIER = new NamedAttributeMap<>("Default", Map.of(), Map.of(), QUALIFIER_LIST);

  private static final List<NamedAttributeMap<?>> DEFAULT_QUALIFIERS = List.of(DEFAULT_QUALIFIER);

  private static final List<NamedAttributeMap<?>> ANY_AND_DEFAULT_QUALIFIERS = List.of(ANY_QUALIFIER, DEFAULT_QUALIFIER);

  private Qualifiers() {
    super();
  }

  /**
   * Returns the <dfn>any qualifier</dfn>.
   *
   * @return the <dfn>any qualifier</dfn>; never {@code null}
   *
   * @see #anyQualifiers()
   */
  public static final NamedAttributeMap<?> anyQualifier() {
    return ANY_QUALIFIER;
  }

  /**
   * Returns {@code true} if and only if the supplied {@link NamedAttributeMap} {@linkplain
   * NamedAttributeMap#equals(Object) is equal to} the {@linkplain #anyQualifier() any qualifier}.
   *
   * @param nam a {@link NamedAttributeMap}; must not be {@code null}
   *
   * @return {@code true} if and only if the supplied {@link NamedAttributeMap} {@linkplain
   * NamedAttributeMap#equals(Object) is equal to} the {@linkplain #defaultQualifier() default qualifier}
   *
   * @exception NullPointerException if {@code nam} is {@code null}
   */
  public static final boolean anyQualifier(final NamedAttributeMap<?> nam) {
    return ANY_QUALIFIER.equals(nam) && qualifier(nam);
  }

  /**
   * Returns an immutable {@link List} consisting solely of the <dfn>any qualifier</dfn>.
   *
   * @return an immutable {@link List}; never {@code null}
   *
   * @see #anyQualifier()
   */
  public static final List<NamedAttributeMap<?>> anyQualifiers() {
    return ANY_QUALIFIERS;
  }

  /**
   * Returns an unmodifiable {@link List} consisting solely of the <dfn>any qualifier</dfn> and the <dfn>default
   * qualifier</dfn>.
   *
   * @return an unmodifiable {@link List} consisting solely of the any qualifier and the default qualifier; never {@code
   * null}
   *
   * @see #anyQualifier()
   *
   * @see #defaultQualifier()
   */
  public static final List<NamedAttributeMap<?>> anyAndDefaultQualifiers() {
    return ANY_AND_DEFAULT_QUALIFIERS;
  }

  /**
   * Returns the <dfn>default qualifier</dfn>.
   *
   * @return the <dfn>default qualifier</dfn>; never {@code null}
   *
   * @see #defaultQualifiers()
   */
  public static final NamedAttributeMap<?> defaultQualifier() {
    return DEFAULT_QUALIFIER;
  }

  /**
   * Returns {@code true} if and only if the supplied {@link NamedAttributeMap} {@linkplain
   * NamedAttributeMap#equals(Object) is equal to} the {@linkplain #defaultQualifier() default qualifier}.
   *
   * @param nam a {@link NamedAttributeMap}; must not be {@code null}
   *
   * @return {@code true} if and only if the supplied {@link NamedAttributeMap} {@linkplain
   * NamedAttributeMap#equals(Object) is equal to} the {@linkplain #defaultQualifier() default qualifier}
   *
   * @exception NullPointerException if {@code nam} is {@code null}
   */
  public static final boolean defaultQualifier(final NamedAttributeMap<?> nam) {
    return DEFAULT_QUALIFIER.equals(nam) && qualifier(nam);
  }

  /**
   * Returns an immutable {@link List} consisting solely of the <dfn>default qualifier</dfn>.
   *
   * @return an immutable {@link List}; never {@code null}
   *
   * @see #defaultQualifier()
   */
  public static final List<NamedAttributeMap<?>> defaultQualifiers() {
    return DEFAULT_QUALIFIERS;
  }

  /**
   * Returns the <dfn>qualifier</dfn> (meta-) qualifier.
   *
   * @return the <dfn>qualifier</dfn> (meta-) qualifier; never {@code null}
   */
  public static final NamedAttributeMap<?> qualifier() {
    return QUALIFIER;
  }

  /**
   * Returns {@code true} if and only if the supplied {@link NamedAttributeMap} is itself a {@link NamedAttributeMap}
   * that can be used to designate other {@link NamedAttributeMap}s as qualifiers, or a {@link NamedAttributeMap} so
   * designated.
   *
   * <p>A {@link NamedAttributeMap} whose {@linkplain NamedAttributeMap#name() name} is {@code Qualifier} and whose
   * {@linkplain NamedAttributeMap#metadata() metadata} is empty is an example of the former.</p>
   *
   * <p>A {@link NamedAttributeMap} whose {@linkplain NamedAttributeMap#metadata() metadata} contains a {@link
   * NamedAttributeMap} whose {@linkplain NamedAttributeMap#name() name} is {@code Qualifier} is an example of the
   * latter.</p>
   *
   * @param q a {@link NamedAttributeMap}; must not be {@code null}
   *
   * @return {@code true} if and only if the supplied {@link NamedAttributeMap} is itself a {@link NamedAttributeMap}
   * that can be used to designate other {@link NamedAttributeMap}s as qualifiers, or a {@link NamedAttributeMap} so
   * designated
   *
   * @exception NullPointerException if {@code nam} is {@code null}
   *
   * @see NamedAttributeMap#metadata()
   */
  public static final boolean qualifier(final NamedAttributeMap<?> q) {
    return q != null && qualifier(q.metadata());
  }

  private static final boolean qualifier(final Iterable<? extends NamedAttributeMap<?>> mds) {
    for (final NamedAttributeMap<?> md : mds) {
      if (QUALIFIER.equals(md) && md.metadata().isEmpty() || qualifier(md)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns an unmodifiable {@link List} consisting only of those {@link NamedAttributeMap}s in the supplied {@link
   * Collection} that {@linkplain #qualifier(NamedAttributeMap) are qualifiers}.
   *
   * @param c a {@link Collection} of {@link NamedAttributeMap}s; must not be {@code null}
   *
   * @return an unmodifiable {@link List} consisting only of those {@link NamedAttributeMap}s in the supplied {@link
   * Collection} that {@linkplain #qualifier(NamedAttributeMap) are qualifiers}; never {@code null}
   *
   * @exception NullPointerException if {@code c} is {@code null}
   */
  public static final List<NamedAttributeMap<?>> qualifiers(final Collection<? extends NamedAttributeMap<?>> c) {
    if (c == null || c.isEmpty()) {
      return List.of();
    }
    final ArrayList<NamedAttributeMap<?>> list = new ArrayList<>(c.size());
    for (final NamedAttributeMap<?> a : c) {
      if (qualifier(a)) {
        list.add(normalize(a));
      }
    }
    list.trimToSize();
    return Collections.unmodifiableList(list);
  }

  /**
   * Returns a {@link NamedAttributeMap} that is {@linkplain NamedAttributeMap#equals(Object) equal to} the supplied
   * {@link NamedAttributeMap}.
   *
   * <p>The returned {@link NamedAttributeMap} may be the supplied {@link NamedAttributeMap} or a different
   * instance.</p>
   *
   * @param nam a {@link NamedAttributeMap}; must not be {@code null}
   *
   * @return a {@link NamedAttributeMap} that is {@linkplain NamedAttributeMap#equals(Object) equal to} the supplied
   * {@link NamedAttributeMap}; never {@code null}
   *
   * @exception NullPointerException if {@code nam} is {@code null}
   */
  public static final NamedAttributeMap<?> normalize(final NamedAttributeMap<?> nam) {
    return switch (nam) {
    case null -> throw new NullPointerException("nam");
    case NamedAttributeMap<?> q when anyQualifier(q) -> anyQualifier();
    case NamedAttributeMap<?> q when defaultQualifier(q) -> defaultQualifier();
    case NamedAttributeMap<?> q when QUALIFIER.equals(q) && q.metadata().isEmpty() -> qualifier();
    default -> nam;
    };
  }

  /**
   * Returns an immutable {@link List} of {@link NamedAttributeMap}s that is {@linkplain List#equals(Object) equal to} the supplied
   * {@link List}.
   *
   * <p>The returned {@link List} may be the supplied {@link List} or a different instance.</p>
   *
   * @param list a {@link List} of {@link NamedAttributeMap}s; must not be {@code null}
   *
   * @return an immutable {@link List} of {@link NamedAttributeMap}s that is {@linkplain List#equals(Object) equal to} the supplied
   * {@link List}; never {@code null}
   *
   * @exception NullPointerException if {@code list} is {@code null}
   */
  public static final List<NamedAttributeMap<?>> normalize(final List<NamedAttributeMap<?>> list) {
    return switch (list) {
    case null -> throw new NullPointerException("list");
    case List<NamedAttributeMap<?>> l when l.size() == 1 && anyQualifier(l.get(0)) -> anyQualifiers();
    case List<NamedAttributeMap<?>> l when l.size() == 1 && defaultQualifier(l.get(0)) -> defaultQualifiers();
    case List<NamedAttributeMap<?>> l when l.size() == 2 && anyQualifier(l.get(0)) && defaultQualifier(l.get(1)) -> anyAndDefaultQualifiers();
    default -> List.copyOf(list);
    };
  }

}
