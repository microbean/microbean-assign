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

import java.util.AbstractList;
import java.util.List;

import javax.lang.model.type.TypeMirror;

/**
 * An immutable {@link AbstractList} of {@link TypeMirror}s sorted in a specific way, intended to store the {@linkplain
 * Types#supertypes(TypeMirror) supertypes} of a {@link TypeMirror}.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see Types#supertypes(TypeMirror)
 */
public final class SupertypeList extends AbstractList<TypeMirror> {

  private final int interfaceIndex;
  
  private final List<TypeMirror> sortedSupertypes;

  SupertypeList(final List<? extends TypeMirror> sortedSupertypes, final int interfaceIndex) {
    super();
    if (sortedSupertypes.isEmpty()) {
      throw new IllegalArgumentException();
    }
    switch (sortedSupertypes) {
    case SupertypeList sl:
      this.sortedSupertypes = sl.sortedSupertypes;
      this.interfaceIndex = sl.interfaceIndex;
      break;
    default:
      this.sortedSupertypes = List.copyOf(sortedSupertypes);
      this.interfaceIndex = interfaceIndex;
    }    
  }

  @Override // AbstractList<TypeMirror>
  public final TypeMirror get(final int index) {
    return this.sortedSupertypes.get(index);
  }

  /**
   * Returns the index of the first {@linkplain javax.lang.model.element.ElementKind#isInterface() interface type} this
   * {@link SupertypeList} contains, or a negative value if it contains no interface types.
   *
   * @return the index of the first {@linkplain javax.lang.model.element.ElementKind#isInterface() interface type} this
   * {@link SupertypeList} contains, or a negative value if it contains no interface types
   */
  public final int interfaceIndex() {
    return this.interfaceIndex;
  }
  
  @Override // AbstractList<TypeMirror>
  public final int size() {
    return this.sortedSupertypes.size();
  }

}
