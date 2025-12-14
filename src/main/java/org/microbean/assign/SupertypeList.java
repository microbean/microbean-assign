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
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.Spliterator;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import java.util.stream.Stream;

import javax.lang.model.type.TypeMirror;

/**
 * An immutable {@link List} of {@link TypeMirror}s sorted in a specific way, intended to store the {@linkplain
 * Types#supertypes(TypeMirror) supertypes} of a {@link TypeMirror}.
 *
 * @author <a href="https://about.me/lairdnelson" target="_top">Laird Nelson</a>
 *
 * @see Types#supertypes(TypeMirror)
 */
public final class SupertypeList implements List<TypeMirror> {

  private static final SupertypeList EMPTY_LIST = new SupertypeList();

  private final int interfaceIndex;

  private final List<TypeMirror> sortedSupertypes;

  private SupertypeList() {
    this(List.of(), -1);
  }

  SupertypeList(final List<? extends TypeMirror> sortedSupertypes, final int interfaceIndex) {
    super();
    if (sortedSupertypes.isEmpty()) {
      if (interfaceIndex >= 0) {
        throw new IllegalArgumentException("sortedSupertypes: " + sortedSupertypes + "; interfaceIndex: " + interfaceIndex);
      }
      this.sortedSupertypes = List.of();
      this.interfaceIndex = interfaceIndex;
    } else {
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
  }

  @Override // List<TypeMirror>
  public final void add(final int index, final TypeMirror t) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean add(final TypeMirror t) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean addAll(final int index, final Collection<? extends TypeMirror> c) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean addAll(final Collection<? extends TypeMirror> c) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final void addFirst(final TypeMirror t) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final void addLast(final TypeMirror t) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final void clear() {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean contains(final Object o) {
    return this.sortedSupertypes.contains(o);
  }

  @Override // List<TypeMirror>
  public final boolean containsAll(final Collection<?> c) {
    return this.sortedSupertypes.containsAll(c);
  }

  @Override // List<TypeMirror>
  public final boolean equals(final Object other) {
    return this.sortedSupertypes.equals(other);
  }

  @Override // List<TypeMirror>
  public final void forEach(final Consumer<? super TypeMirror> c) {
    this.sortedSupertypes.forEach(c);
  }

  @Override // List<TypeMirror>
  public final TypeMirror get(final int index) {
    return this.sortedSupertypes.get(index);
  }

  @Override // List<TypeMirror>
  public final int hashCode() {
    return this.sortedSupertypes.hashCode();
  }

  @Override // List<TypeMirror>
  public final int indexOf(final Object o) {
    return this.sortedSupertypes.indexOf(o);
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

  @Override // List<TypeMirror>
  public final boolean isEmpty() {
    return this.sortedSupertypes.isEmpty();
  }

  @Override // List<TypeMirror>
  public final Iterator<TypeMirror> iterator() {
    return this.sortedSupertypes.iterator();
  }

  @Override // List<TypeMirror>
  public final int lastIndexOf(final Object o) {
    return this.sortedSupertypes.lastIndexOf(o);
  }

  @Override // List<TypeMirror>
  public final ListIterator<TypeMirror> listIterator() {
    return this.sortedSupertypes.listIterator();
  }

  @Override // List<TypeMirror>
  public final ListIterator<TypeMirror> listIterator(final int index) {
    return this.sortedSupertypes.listIterator(index);
  }

  @Override // List<TypeMirror>
  public final Stream<TypeMirror> parallelStream() {
    return this.sortedSupertypes.parallelStream();
  }

  @Override // List<TypeMirror>
  public final TypeMirror remove(final int index) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean remove(final Object o) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean removeAll(final Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final TypeMirror removeFirst() {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean removeIf(final Predicate<? super TypeMirror> p) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final TypeMirror removeLast() {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final void replaceAll(final UnaryOperator<TypeMirror> op) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final boolean retainAll(final Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final TypeMirror set(final int index, final TypeMirror t) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final int size() {
    return this.sortedSupertypes.size();
  }

  @Override // List<TypeMirror>
  public final void sort(final Comparator<? super TypeMirror> c) {
    throw new UnsupportedOperationException();
  }

  @Override // List<TypeMirror>
  public final Spliterator<TypeMirror> spliterator() {
    return this.sortedSupertypes.spliterator();
  }

  @Override // List<TypeMirror>
  public final Stream<TypeMirror> stream() {
    return this.sortedSupertypes.stream();
  }

  @Override // List<TypeMirror>
  public final List<TypeMirror> subList(final int from, final int to) {
    return this.sortedSupertypes.subList(from, to);
  }

  @Override // List<TypeMirror>
  public final Object[] toArray() {
    return this.sortedSupertypes.toArray();
  }

  @Override // List<TypeMirror>
  public final <T> T[] toArray(final IntFunction<T[]> g) {
    return this.sortedSupertypes.toArray(g);
  }

  @Override // List<TypeMirror>
  public final <T> T[] toArray(final T[] a) {
    return this.sortedSupertypes.toArray(a);
  }

  @Override // Object
  public final String toString() {
    return this.sortedSupertypes.toString();
  }

  /**
   * Returns a non-{@code null} {@linkplain #isEmpty() empty} {@link SupertypeList}.
   *
   * <p>This is useful primarily for testing and edge cases.</p>
   *
   * @return a non-{@code null} {@linkplain #isEmpty() empty} {@link SupertypeList}
   */
  public static final SupertypeList of() {
    return EMPTY_LIST;
  }

}
