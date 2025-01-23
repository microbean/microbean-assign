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

import javax.lang.model.type.TypeKind;

/**
 * A {@link Comparator} establishing a <dfn>partial order</dfn> over {@link TypeKind}s such that {@linkplain
 * TypeKind#TYPEVAR type variables} precede all other kinds of types.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see #compare(TypeKind, TypeKind)
 */
public final class TypeVariablesFirstTypeKindComparator implements Comparator<TypeKind> {

  /**
   * The sole instance of this class.
   */
  public static final TypeVariablesFirstTypeKindComparator INSTANCE = new TypeVariablesFirstTypeKindComparator();
  
  private TypeVariablesFirstTypeKindComparator() {
    super();
  }

  /**
   * Compares the two {@link TypeKind}s and returns the result.
   *
   * <p>If {@code k0 == k1}, returns {@code 0}.</p>
   *
   * <p>If {@code k0} is {@code null} and {@code k1} is not, returns a positive value.</p>
   *
   * <p>If {@code k0} is {@link TypeKind#TYPEVAR} or {@code k1} is {@code null} and {@code k0} is not, returns a
   * negative value.</p>
   *
   * <p>Returns {@code 0} in all other cases.
   *
   * @param k0 a {@link TypeKind}; may be {@code null}
   *
   * @param k1 a {@link TypeKind}; may be {@code null}
   *
   * @return a comparison result
   *
   * @see TypeKind#TYPEVAR
   */
  @Override // Comparator<TypeKind>
  public final int compare(final TypeKind k0, final TypeKind k1) {
    if (k0 == k1) {
      return 0;
    } else if (k0 == null) {
      return 1;
    } else if (k0 == TypeKind.TYPEVAR || k1 == null) {
      return -1;
    } else {
      return 0;
    }
  }

}
