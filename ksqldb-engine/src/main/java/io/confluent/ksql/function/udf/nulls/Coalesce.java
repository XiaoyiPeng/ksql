/*
 * Copyright 2020 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"; you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.function.udf.nulls;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import java.util.Arrays;
import java.util.Objects;

/**
 * Returns first non-null element
 */
@SuppressWarnings("MethodMayBeStatic") // UDF methods can not be static.
@UdfDescription(name = "COALESCE", description = "Returns first non-null element")
public class Coalesce {

  @SuppressWarnings("varargs")
  @SafeVarargs
  @Udf
  public final <T> T coalesce(final T first, final T... others) {
    if (first != null) {
      return first;
    }

    if (others == null) {
      return null;
    }

    return Arrays.stream(others)
        .filter(Objects::nonNull)
        .findFirst()
        .orElse(null);
  }
}
