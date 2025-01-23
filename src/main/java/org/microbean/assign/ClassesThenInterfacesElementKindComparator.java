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

import java.util.Comparator;

import javax.lang.model.element.ElementKind;

/**
 * A {@link Comparator} establishing a <dfn>partial order</dfn> over {@link ElementKind}s such that {@linkplain
 * ElementKind#isClass() classes} precede {@linkplain ElementKind#isInterface() interfaces}.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see #compare(ElementKind, ElementKind)
 */
public final class ClassesThenInterfacesElementKindComparator implements Comparator<ElementKind> {

  /**
   * The sole instance of this class.
   */
  public static final ClassesThenInterfacesElementKindComparator INSTANCE = new ClassesThenInterfacesElementKindComparator();
  
  private ClassesThenInterfacesElementKindComparator() {
    super();
  }

  /**
   * Compares the two {@link ElementKind}s and returns the result.
   *
   * <p>If {@code k0 == k1}, returns {@code 0}.</p>
   *
   * <p>If {@code k0} is {@code null} and {@code k1} is not, returns a positive value.</p>
   *
   * <p>If {@code k0} {@linkplain ElementKind#isClass() is a class} and {@code k1} {@linkplain ElementKind#isInterface()
   * is an interface}, returns a negative value.</p>
   *
   * <p>If {@code k0} {@linkplain ElementKind#isInterface() is an interface} and {@code k1} {@linkplain
   * ElementKind#isClass() is a class}, returns a positive value.</p>
   *
   * <p>Returns {@code 0} in all other cases.
   *
   * @param k0 an {@link ElementKind}; may be {@code null}
   *
   * @param k1 an {@link ElementKind}; may be {@code null}
   *
   * @return a comparison result
   *
   * @see ElementKind#isClass()
   *
   * @see ElementKind#isInterface()
   */
  @Override // Comparator<ElementKind>
  public final int compare(final ElementKind k0, final ElementKind k1) {
    if (k0 == k1) {
      return 0;
    } else if (k0 == null) {
      return 1;
    } else if (k1 == null) {
      return -1;
    } else if (k0.isClass() && k1.isInterface()) {
      return -1;
    } else if (k0.isInterface() && k1.isClass()) {
      return 1;
    } else {
      return 0;
    }
  }

}
