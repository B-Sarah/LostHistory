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
 * Model definition for Battle.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the battleEndpoint. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Battle extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<DateFormatted> formattedDates;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<ParticpantFormatted> formattedParticipants;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> participants;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Coordinate> places;

  static {
    // hack to force ProGuard to consider Coordinate used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(Coordinate.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer year;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<DateFormatted> getFormattedDates() {
    return formattedDates;
  }

  /**
   * @param formattedDates formattedDates or {@code null} for none
   */
  public Battle setFormattedDates(java.util.List<DateFormatted> formattedDates) {
    this.formattedDates = formattedDates;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<ParticpantFormatted> getFormattedParticipants() {
    return formattedParticipants;
  }

  /**
   * @param formattedParticipants formattedParticipants or {@code null} for none
   */
  public Battle setFormattedParticipants(java.util.List<ParticpantFormatted> formattedParticipants) {
    this.formattedParticipants = formattedParticipants;
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
  public Battle setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getParticipants() {
    return participants;
  }

  /**
   * @param participants participants or {@code null} for none
   */
  public Battle setParticipants(java.util.List<java.lang.String> participants) {
    this.participants = participants;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Coordinate> getPlaces() {
    return places;
  }

  /**
   * @param places places or {@code null} for none
   */
  public Battle setPlaces(java.util.List<Coordinate> places) {
    this.places = places;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getYear() {
    return year;
  }

  /**
   * @param year year or {@code null} for none
   */
  public Battle setYear(java.lang.Integer year) {
    this.year = year;
    return this;
  }

  @Override
  public Battle set(String fieldName, Object value) {
    return (Battle) super.set(fieldName, value);
  }

  @Override
  public Battle clone() {
    return (Battle) super.clone();
  }

}
