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

/**
 * An interface whose implementations are affiliated with an {@link AttributedType}.
 *
 * @author <a href="https://about.me/lairdnelson/" target="_top">Laird Nelson</a>
 *
 * @see #attributedType()
 */
public interface AttributedTyped {

  /**
   * Returns the {@link AttributedType} with which this {@link AttributedTyped} instance is affiliated.
   *
   * <p>Implementations of this method must not return {@code null}.</p>
   *
   * <p>Implementations of this method must return a determinate value.</p>
   *
   * <p>Implementations of this method must be safe for concurrent use by multiple threads.</p>
   *
   * @return a non-{@code null} {@link AttributedType}
   *
   * @see AttributedType
   */
  public AttributedType attributedType();

}
