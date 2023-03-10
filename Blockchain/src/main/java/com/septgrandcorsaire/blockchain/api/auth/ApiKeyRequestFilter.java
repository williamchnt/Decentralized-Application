package com.septgrandcorsaire.blockchain.api.auth;

/*
@Component
public class ApiKeyRequestFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ApiKeyRequestFilter.class);

    private ApiKeyRepository apiKeyRepository;

    public ApiKeyRequestFilter() {
        apiKeyRepository = ApiKeyRepository.INSTANCE;
    }

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
        MyRequestWrapper myRequestWrapper = new MyRequestWrapper(currentRequest);
        String path = currentRequest.getRequestURI();

        String key = currentRequest.getHeader("Key") == null ? "" : currentRequest.getHeader("Key");

        if (path.equals("/smart-vote/api/v1/get-sandbox/")) {
            String electionName = "sandbox";
            boolean isExactApiKey = this.apiKeyRepository.isApiKeyCorrespondingToElection(key, electionName);
            handleApiKeyAccuracy(servletResponse, chain, myRequestWrapper, isExactApiKey, electionName);
            return;
        }
        if (!path.startsWith("/smart-vote/api/v1/vote")) { //todo equals ?
            chain.doFilter(myRequestWrapper, servletResponse);
            return;
        }

        String electionName = getElectionNameFromBody(myRequestWrapper.getReader().lines().reduce("", String::concat));

        boolean isExactApiKey = false;
        try {
            isExactApiKey = this.apiKeyRepository.isApiKeyCorrespondingToElection(key, electionName);
        } catch (ElectionNotFoundException e) {
            returnNotFoundMessage(servletResponse, e.getMessage());
            return;
        }
        handleApiKeyAccuracy(servletResponse, chain, myRequestWrapper, isExactApiKey, electionName);
    }

    private static void handleApiKeyAccuracy(
            ServletResponse servletResponse,
            FilterChain chain,
            MyRequestWrapper myRequestWrapper,
            boolean isExactApiKey, String electionName) throws IOException, ServletException {
        if (isExactApiKey) {
            chain.doFilter(myRequestWrapper, servletResponse);
        } else {
            returnInvalidApiKey(servletResponse, electionName);
            //throw new InvalidApiKeyException(String.format(ErrorCode.INVALID_PARAMETER.getDefaultMessage(), electionName));
        }
    }

    private static void returnNotFoundMessage(ServletResponse servletResponse, String message) throws IOException {
        ErrorResource errorResource = new ErrorResource(ErrorCode.NOT_FOUND_ELECTION.getValue(), message);
        String jsonError = JsonService.toJson(errorResource);

        returnErrorMessage(servletResponse, jsonError, HttpServletResponse.SC_NOT_FOUND);
    }

    private static void returnInvalidApiKey(ServletResponse servletResponse, String electionName) throws IOException {
        String errorMessage = String.format(ErrorCode.INVALID_API_KEY.getDefaultMessage(), electionName);
        ErrorResource errorResource = new ErrorResource(ErrorCode.INVALID_API_KEY.getValue(), errorMessage);
        String jsonError = JsonService.toJson(errorResource);

        returnErrorMessage(servletResponse, jsonError, HttpServletResponse.SC_UNAUTHORIZED);
    }

    private static void returnErrorMessage(ServletResponse servletResponse, String error, int code) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.reset();
        resp.setStatus(code);
        servletResponse.setContentLength(error.length());
        servletResponse.getWriter().write(error);
    }

    private String getElectionNameFromBody(String body) {
        VotePayload votePayload = JsonService.votePayloadfromJson(body);
        return votePayload.getElectionName();

    }

}
*/