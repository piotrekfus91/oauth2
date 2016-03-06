package pl.iaeste.caseweek.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class DropboxFilter implements Filter {
    private static final String CLIENT_ID = "m6xbnhqrt3gss12";
    private static final String CLIENT_SECRET = "n7m71z5gbrvsfse";
    private static final String REDIRECT_URI = "http://localhost:8080";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String token = (String) httpServletRequest.getSession().getAttribute("token");

        if(token == null) {
            log.info("token is null, doing OAuth flow");

            try {
                doOAuth(httpServletRequest, httpServletResponse, chain);
            } catch (OAuthSystemException | OAuthProblemException e) {
                log.error(e.getMessage(), e);
            }

        } else {
            log.info("token is {}", token);
            chain.doFilter(request, response);
        }
    }

    private void doOAuth(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, OAuthSystemException, OAuthProblemException {
        String code = request.getParameter("code");
        if(code != null) {
            exchangeCodeForToken(code, request);
            response.sendRedirect(request.getRequestURL().toString());
        } else {
            requestCode(response);
        }
    }

    private void requestCode(HttpServletResponse response) throws OAuthSystemException, IOException {
        OAuthClientRequest oauthRequest = OAuthClientRequest
                .authorizationLocation("https://www.dropbox.com/1/oauth2/authorize")
                .setClientId(CLIENT_ID)
                .setRedirectURI(REDIRECT_URI)
                .setResponseType("code")
                .buildQueryMessage();

        log.info("redirecting user to authorize in dropbox: {}", oauthRequest.getLocationUri());

        response.sendRedirect(oauthRequest.getLocationUri());
    }

    private void exchangeCodeForToken(String code, HttpServletRequest request) throws OAuthSystemException, OAuthProblemException {
        log.info("code is {}", code);

        OAuthClientRequest oAuthRequest = OAuthClientRequest
                .tokenLocation("https://api.dropboxapi.com/1/oauth2/token")
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setCode(code)
                .setRedirectURI(REDIRECT_URI)
                .buildBodyMessage();

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthJSONAccessTokenResponse oauthResponse = oAuthClient.accessToken(oAuthRequest, "POST");

        String token = oauthResponse.getAccessToken();
        request.getSession(true).setAttribute("token", token);
    }
}
