/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-10-17 16:43:55 UTC)
 * on 2017-01-02 at 20:09:13 UTC 
 * Modify at your own risk.
 */

package com.appspot.lost_history.battleEndpoint.model;

/**
 * Model definition for ParticpantFormatted.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the battleEndpoint. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ParticpantFormatted extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean correct;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String name;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getCorrect() {
    return correct;
  }

  /**
   * @param correct correct or {@code null} for none
   */
  public ParticpantFormatted setCorrect(java.lang.Boolean correct) {
    this.correct = correct;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * @param name name or {@code null} for none
   */
  public ParticpantFormatted setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  @Override
  public ParticpantFormatted set(String fieldName, Object value) {
    return (ParticpantFormatted) super.set(fieldName, value);
  }

  @Override
  public ParticpantFormatted clone() {
    return (ParticpantFormatted) super.clone();
  }

}
