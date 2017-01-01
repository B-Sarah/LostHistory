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
 * on 2017-01-01 at 16:07:33 UTC 
 * Modify at your own risk.
 */

package com.appspot.lost_history.battleEndpoint;

/**
 * Service definition for BattleEndpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link BattleEndpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class BattleEndpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.21.0 of the battleEndpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://lost-history.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "battleEndpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public BattleEndpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  BattleEndpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * An accessor for creating requests from the Battle collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code BattleEndpoint battleEndpoint = new BattleEndpoint(...);}
   *   {@code BattleEndpoint.Battle.List request = battleEndpoint.battle().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public Battle battle() {
    return new Battle();
  }

  /**
   * The "battle" collection of methods.
   */
  public class Battle {

    /**
     * Create a request for the method "battle.request".
     *
     * This request holds the parameters needed by the battleEndpoint server.  After setting any
     * optional parameters, call the {@link Request#execute()} method to invoke the remote operation.
     *
     * @param nbBattles
     * @return the request
     */
    public Request request(java.lang.Integer nbBattles) throws java.io.IOException {
      Request result = new Request(nbBattles);
      initialize(result);
      return result;
    }

    public class Request extends BattleEndpointRequest<com.appspot.lost_history.battleEndpoint.model.BattleCollection> {

      private static final String REST_PATH = "battle";

      /**
       * Create a request for the method "battle.request".
       *
       * This request holds the parameters needed by the the battleEndpoint server.  After setting any
       * optional parameters, call the {@link Request#execute()} method to invoke the remote operation.
       * <p> {@link
       * Request#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param nbBattles
       * @since 1.13
       */
      protected Request(java.lang.Integer nbBattles) {
        super(BattleEndpoint.this, "GET", REST_PATH, null, com.appspot.lost_history.battleEndpoint.model.BattleCollection.class);
        this.nbBattles = com.google.api.client.util.Preconditions.checkNotNull(nbBattles, "Required parameter nbBattles must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public Request setAlt(java.lang.String alt) {
        return (Request) super.setAlt(alt);
      }

      @Override
      public Request setFields(java.lang.String fields) {
        return (Request) super.setFields(fields);
      }

      @Override
      public Request setKey(java.lang.String key) {
        return (Request) super.setKey(key);
      }

      @Override
      public Request setOauthToken(java.lang.String oauthToken) {
        return (Request) super.setOauthToken(oauthToken);
      }

      @Override
      public Request setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Request) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Request setQuotaUser(java.lang.String quotaUser) {
        return (Request) super.setQuotaUser(quotaUser);
      }

      @Override
      public Request setUserIp(java.lang.String userIp) {
        return (Request) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.Integer nbBattles;

      /**

       */
      public java.lang.Integer getNbBattles() {
        return nbBattles;
      }

      public Request setNbBattles(java.lang.Integer nbBattles) {
        this.nbBattles = nbBattles;
        return this;
      }

      @Override
      public Request set(String parameterName, Object value) {
        return (Request) super.set(parameterName, value);
      }
    }

  }

  /**
   * Builder for {@link BattleEndpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link BattleEndpoint}. */
    @Override
    public BattleEndpoint build() {
      return new BattleEndpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link BattleEndpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setBattleEndpointRequestInitializer(
        BattleEndpointRequestInitializer battleendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(battleendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
