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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.microbean.attributes.Attributes;

/**
 * A utility class for working with commonly-used <dfn>qualifiers</dfn>.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see Attributes
 */
public final class Qualifiers {

  private static final Attributes QUALIFIER = Attributes.of("Qualifier");

  private static final List<Attributes> QUALIFIERS = List.of(QUALIFIER);

  private static final Attributes ANY_QUALIFIER = Attributes.of("Any", QUALIFIERS);

  private static final List<Attributes> ANY_QUALIFIERS = List.of(ANY_QUALIFIER);

  private static final Attributes DEFAULT_QUALIFIER = Attributes.of("Default", QUALIFIERS);

  private static final List<Attributes> DEFAULT_QUALIFIERS = List.of(DEFAULT_QUALIFIER);

  private static final List<Attributes> ANY_AND_DEFAULT_QUALIFIERS = List.of(ANY_QUALIFIER, DEFAULT_QUALIFIER);

  private static final Attributes PRIMORDIAL_QUALIFIER = Attributes.of("Primordial", QUALIFIERS);

  private static final List<Attributes> PRIMORDIAL_QUALIFIERS = List.of(PRIMORDIAL_QUALIFIER);

  private Qualifiers() {
    super();
  }

  /**
   * Returns an unmodifiable {@link List} consisting solely of the unattributed <dfn>any qualifier</dfn> and the
   * <dfn>default qualifier</dfn>.
   *
   * @return an unmodifiable {@link List} consisting solely of the unattributed any qualifier and the default qualifier;
   * never {@code null}
   *
   * @see #anyQualifier()
   *
   * @see #defaultQualifier()
   */
  public static final List<Attributes> anyAndDefaultQualifiers() {
    return ANY_AND_DEFAULT_QUALIFIERS;
  }

  /**
   * Returns the unattributed <dfn>any qualifier</dfn>.
   *
   * @return the <dfn>any qualifier</dfn>; never {@code null}
   *
   * @see #anyQualifiers()
   */
  public static final Attributes anyQualifier() {
    return ANY_QUALIFIER;
  }

  /**
   * Returns {@code true} if and only if the supplied {@link Attributes} {@linkplain Attributes#equals(Object) is equal
   * to} the unattributed {@linkplain #anyQualifier() any qualifier}.
   *
   * @param a an {@link Attributes}; must not be {@code null}
   *
   * @return {@code true} if and only if the supplied {@link Attributes} {@linkplain Attributes#equals(Object) is equal
   * to} the unattributed {@linkplain #anyQualifier() any qualifier}
   *
   * @exception NullPointerException if {@code a} is {@code null}
   */
  public static final boolean anyQualifier(final Attributes a) {
    return ANY_QUALIFIER == a || anyQualifier().equals(a) && qualifier(a);
  }

  /**
   * Returns an immutable {@link List} consisting solely of the unattributed <dfn>any qualifier</dfn>.
   *
   * @return an immutable {@link List}; never {@code null}
   *
   * @see #anyQualifier()
   */
  public static final List<Attributes> anyQualifiers() {
    return ANY_QUALIFIERS;
  }

  /**
   * Returns the <dfn>default qualifier</dfn>.
   *
   * @return the <dfn>default qualifier</dfn>; never {@code null}
   *
   * @see #defaultQualifiers()
   */
  public static final Attributes defaultQualifier() {
    return DEFAULT_QUALIFIER;
  }

  /**
   * Returns {@code true} if and only if the supplied {@link Attributes} {@linkplain
   * Attributes#equals(Object) is equal to} the {@linkplain #defaultQualifier() default qualifier}.
   *
   * @param a an {@link Attributes}; must not be {@code null}
   *
   * @return {@code true} if and only if the supplied {@link Attributes} {@linkplain
   * Attributes#equals(Object) is equal to} the {@linkplain #defaultQualifier() default qualifier}
   *
   * @exception NullPointerException if {@code a} is {@code null}
   */
  public static final boolean defaultQualifier(final Attributes a) {
    return DEFAULT_QUALIFIER == a || defaultQualifier().equals(a) && qualifier(a);
  }

  /**
   * Returns an immutable {@link List} consisting solely of the <dfn>default qualifier</dfn>.
   *
   * @return an immutable {@link List}; never {@code null}
   *
   * @see #defaultQualifier()
   */
  public static final List<Attributes> defaultQualifiers() {
    return DEFAULT_QUALIFIERS;
  }

  /**
   * Returns an {@link Attributes} that is {@linkplain Attributes#equals(Object) equal to} the supplied {@link
   * Attributes}.
   *
   * <p>The returned {@link Attributes} may be the supplied {@link Attributes} or a different instance.</p>
   *
   * @param a an {@link Attributes}; must not be {@code null}
   *
   * @return an {@link Attributes} that is {@linkplain Attributes#equals(Object) equal to} the supplied {@link
   * Attributes}; never {@code null}
   *
   * @exception NullPointerException if {@code a} is {@code null}
   */
  public static final Attributes normalize(final Attributes a) {
    return switch (a) {
    case null -> throw new NullPointerException("a");
    case Attributes q when defaultQualifier(q) -> defaultQualifier();
    case Attributes q when QUALIFIER.equals(q) -> qualifier();
    default -> a;
    };
  }

  /**
   * Returns an immutable {@link List} of {@link Attributes}s that is {@linkplain List#equals(Object) equal to} the
   * supplied {@link List}.
   *
   * <p>The returned {@link List} may be the supplied {@link List} or a different instance.</p>
   *
   * @param list a {@link List} of {@link Attributes}s; must not be {@code null}
   *
   * @return an immutable {@link List} of {@link Attributes}s that is {@linkplain List#equals(Object) equal to} the
   * supplied {@link List}; never {@code null}
   *
   * @exception NullPointerException if {@code list} is {@code null}
   */
  public static final List<Attributes> normalize(final List<Attributes> list) {
    return switch (list.size()) {
    case 0 -> List.of();
    case 1 -> list.equals(defaultQualifiers()) ? defaultQualifiers() : List.copyOf(list);
    default -> {
      final List<Attributes> l = new ArrayList<>(list.size());
      for (final Attributes a : list) {
        l.add(normalize(a));
      }
      yield Collections.unmodifiableList(l);
    }
    };
  }

  /**
   * Returns the <dfn>primordial qualifier</dfn>.
   *
   * @return the <dfn>primordial qualifier</dfn>; never {@code null}
   *
   * @see #primordialQualifiers()
   */
  public static final Attributes primordialQualifier() {
    return PRIMORDIAL_QUALIFIER;
  }

  /**
   * Returns {@code true} if and only if the supplied {@link Attributes} {@linkplain
   * Attributes#equals(Object) is equal to} the {@linkplain #primordialQualifier() primordial qualifier}.
   *
   * @param a an {@link Attributes}; must not be {@code null}
   *
   * @return {@code true} if and only if the supplied {@link Attributes} {@linkplain
   * Attributes#equals(Object) is equal to} the {@linkplain #primordialQualifier() primordial qualifier}
   *
   * @exception NullPointerException if {@code a} is {@code null}
   */
  public static final boolean primordialQualifier(final Attributes a) {
    return PRIMORDIAL_QUALIFIER == a || primordialQualifier().equals(a) && qualifier(a);
  }

  /**
   * Returns an immutable {@link List} consisting solely of the <dfn>primordial qualifier</dfn>.
   *
   * @return an immutable {@link List}; never {@code null}
   *
   * @see #primordialQualifier()
   */
  public static final List<Attributes> primordialQualifiers() {
    return PRIMORDIAL_QUALIFIERS;
  }

  /**
   * Returns the <dfn>qualifier</dfn> (meta-) qualifier.
   *
   * @return the <dfn>qualifier</dfn> (meta-) qualifier; never {@code null}
   */
  public static final Attributes qualifier() {
    return QUALIFIER;
  }

  /**
   * Returns {@code true} if and only if the supplied {@link Attributes} is an {@link Attributes} that can be used to
   * designate other {@link Attributes} as qualifiers.
   *
   * @param q an {@link Attributes}; must not be {@code null}
   *
   * @return {@code true} if and only if the supplied {@link Attributes} is an {@link Attributes} that can be used to
   * designate other {@link Attributes} as qualifiers
   *
   * @exception NullPointerException if {@code q} is {@code null}
   */
  public static final boolean qualifier(final Attributes q) {
    return q.attributes().contains(qualifier());
  }

  /**
   * Returns an immutable {@link List} consisting solely of the <dfn>qualifier</dfn> (meta-) qualifier.
   *
   * @return an immutable {@link List}; never {@code null}
   *
   * @see #qualifier()
   */
  public static final List<Attributes> qualifiers() {
    return QUALIFIERS;
  }

  /**
   * Returns an unmodifiable {@link List} consisting only of those {@link Attributes} in the supplied {@link
   * Collection} that {@linkplain #qualifier(Attributes) are qualifiers}.
   *
   * @param c a {@link Collection} of {@link Attributes}s; must not be {@code null}
   *
   * @return an unmodifiable {@link List} consisting only of those {@link Attributes}s in the supplied {@link
   * Collection} that {@linkplain #qualifier(Attributes) are qualifiers}; never {@code null}
   *
   * @exception NullPointerException if {@code c} is {@code null}
   */
  public static final List<Attributes> qualifiers(final Collection<? extends Attributes> c) {
    return switch (c) {
    case Collection<?> c0 when c0.isEmpty() -> List.of();
    case Collection<?> c0 when c0.equals(defaultQualifiers()) -> defaultQualifiers();
    case Collection<?> c0 when c0.equals(anyAndDefaultQualifiers()) -> anyAndDefaultQualifiers();
    default ->{
      final ArrayList<Attributes> list = new ArrayList<>(c.size());
      for (final Attributes a : c) {
        if (qualifier(a)) {
          list.add(normalize(a));
        }
      }
      list.trimToSize();
      yield Collections.unmodifiableList(list);
    }
    };
  }

}
