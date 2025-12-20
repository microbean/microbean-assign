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
 * A utility class for working with <dfn>qualifiers</dfn>.
 *
 * <p>This class is currently not used by other classes in this package. It may be useful in a variety of dependency
 * injection systems.</p>
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see Attributes
 */
public class Qualifiers {


  /*
   * Static fields.
   */


  private static final Attributes QUALIFIER = Attributes.of("Qualifier");

  private static final List<Attributes> QUALIFIERS = List.of(QUALIFIER);


  /*
   * Constructors.
   */


  /**
   * Creates a new {@link Qualifiers}.
   */
  public Qualifiers() {
    super();
  }


  /*
   * Instance methods.
   */


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
  public Attributes normalize(final Attributes a) {
    return switch (a) {
    case null -> throw new NullPointerException("a");
    case Attributes q when this.qualifier().equals(q) -> this.qualifier();
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
  public List<Attributes> normalize(final List<Attributes> list) {
    return switch (list.size()) {
    case 0 -> List.of();
    case 1 -> list.equals(this.qualifiers()) ? this.qualifiers() : List.copyOf(list);
    default -> {
      final List<Attributes> l = new ArrayList<>(list.size());
      for (final Attributes a : list) {
        l.add(this.normalize(a));
      }
      yield Collections.unmodifiableList(l);
    }
    };
  }

  /**
   * Returns the <dfn>qualifier</dfn> (meta-) qualifier.
   *
   * @return the <dfn>qualifier</dfn> (meta-) qualifier; never {@code null}
   */
  public Attributes qualifier() {
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
  public boolean qualifier(final Attributes q) {
    return q.attributes().contains(this.qualifier());
  }

  /**
   * Returns an immutable {@link List} consisting solely of the <dfn>qualifier</dfn> (meta-) qualifier.
   *
   * @return an immutable {@link List}; never {@code null}
   *
   * @see #qualifier()
   */
  public List<Attributes> qualifiers() {
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
  public List<Attributes> qualifiers(final Collection<? extends Attributes> c) {
    return switch (c) {
    case Collection<?> c0 when c0.isEmpty() -> List.of();
    default -> {
      final ArrayList<Attributes> list = new ArrayList<>(c.size());
      for (final Attributes a : c) {
        if (this.qualifier(a)) {
          list.add(this.normalize(a));
        }
      }
      list.trimToSize();
      yield Collections.unmodifiableList(list);
    }
    };
  }

}
