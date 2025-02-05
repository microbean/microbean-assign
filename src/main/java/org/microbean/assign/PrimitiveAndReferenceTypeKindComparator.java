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
 * A {@link Comparator} establishing a <a href="https://en.wikipedia.org/wiki/Partially_ordered_set"><dfn>partial
 * order</dfn></a> over {@link TypeKind}s such that {@linkplain TypeKind#TYPEVAR type variables} precede {@linkplain
 * TypeKind#isPrimitive primitive types}, {@linkplain TypeKind#isPrimitive primitive types} precede {@linkplain
 * TypeKind#ARRAY array types}, and {@linkplain TypeKind#ARRAY array types} precede {@linkplain TypeKind#DECLARED
 * declared types}.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see #compare(TypeKind, TypeKind)
 */
public final class PrimitiveAndReferenceTypeKindComparator implements Comparator<TypeKind> {


  /*
   * Static fields.
   */


  /**
   * The sole instance of this class.
   */
  public static final PrimitiveAndReferenceTypeKindComparator INSTANCE = new PrimitiveAndReferenceTypeKindComparator();


  /*
   * Constructors.
   */


  private PrimitiveAndReferenceTypeKindComparator() {
    super();
  }


  /*
   * Instance methods.
   */


  /**
   * Compares the two {@link TypeKind}s and returns the result.
   *
   * <p>If {@code k0} is {@code null} and {@code k1} is not, returns a positive value.</p>
   *
   * <p>If {@code k1} is {@code null} and {@code k0} is not, returns a negative value.</p>
   *
   * <p>If {@code k0} is {@link TypeKind#ARRAY}, and {@code k1} {@linkplain TypeKind#isPrimitive() is primitive},
   * returns a positive value.</p>
   *
   * <p>If {@code k0} is {@link TypeKind#ARRAY}, and {@code k1} is {@link TypeKind#DECLARED}, returns a negative
   * value.</p>
   *
   * <p>If {@code k0} {@linkplain TypeKind#isPrimitive() is primitive}, and {@code k1} is {@link TypeKind#ARRAY} or
   * {@link TypeKind#DECLARED}, returns a negative value.</p>
   *
   * <p>If {@code k0} {@linkplain TypeKind#isPrimitive() is primitive}, and {@code k1} is {@link TypeKind#TYPEVAR},
   * returns a positive value.</p>
   *
   * <p>If {@code k0} is {@link TypeKind#DECLARED}, and {@code k1} is {@link TypeKind#ARRAY}, or {@linkplain
   * TypeKind#isPrimitive() is primitive}, or is {@link TypeKind#TYPEVAR}, returns a positive value.</p>
   *
   * <p>If {@code k0} is {@link TypeKind#TYPEVAR}, and {@code k1} is {@link TypeKind#ARRAY}, or {@linkplain
   * TypeKind#isPrimitive() is primitive}, or is {@link TypeKind#DECLARED}, returns a negative value.</p>
   *
   * <p>Returns {@code 0} in all other cases.
   *
   * @param k0 a {@link TypeKind}; may be {@code null}
   *
   * @param k1 a {@link TypeKind}; may be {@code null}
   *
   * @return a comparison result
   *
   * @see TypeKind#isPrimitive()
   *
   * @see TypeKind#ARRAY
   *
   * @see TypeKind#DECLARED
   *
   * @see TypeKind#TYPEVAR
   */
  @Override // Comparator<TypeKind>
  public final int compare(final TypeKind k0, final TypeKind k1) {
    return k0 == k1 ? 0 : k0 == null ? 1 : k1 == null ? -1 : switch (k0) {

    case ARRAY -> switch (k1) {
    // Arrays come after primitives and type variables.
    case BOOLEAN, BYTE, CHAR, DOUBLE, FLOAT, INT, LONG, SHORT, TYPEVAR -> 1;
    // Arrays come before declared types.
    case DECLARED -> -1; // arrays come before declared
    default -> 0;
    };

    case BOOLEAN, BYTE, CHAR, DOUBLE, FLOAT, INT, LONG, SHORT -> switch (k1) {
    // Primitives come before arrays and declared types.
    case ARRAY, DECLARED -> -1;
    // Primitives come after type variables.
    case TYPEVAR -> 1;
    default -> 0;
    };

    case DECLARED -> switch (k1) {
    // Declared types come after arrays, primitives and type variables.
    case ARRAY, BOOLEAN, BYTE, CHAR, DOUBLE, FLOAT, INT, LONG, SHORT, TYPEVAR -> 1;
    default -> 0;
    };

    case TYPEVAR -> switch (k1) {
      // Type variables come before arrays, primitives and declared types.
    case ARRAY, BOOLEAN, BYTE, CHAR, DECLARED, DOUBLE, FLOAT, INT, LONG, SHORT -> -1;
    default -> 0;
    };

    default -> 0;
    };
  }

}
