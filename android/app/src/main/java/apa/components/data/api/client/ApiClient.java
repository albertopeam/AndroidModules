package apa.components.data.api.client;

/**
 * Created by alberto on 21/2/16.
 */
public class ApiClient {

/*
    private static final int INVALID_AUTH_CODE = 401;


    public <T> T execute(Call<T> call) throws LoginApiException {
        Response<T> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new MarvelApiException("Network error", e);
        }
        if (response.isSuccess()) {
            return response.body();
        } else {
            parseError(response);
            return null;
        }
    }

    private void parseError(Response execute) throws MarvelApiException {
        String marvelCode = "";
        String marvelDescription = "";
        if (execute.errorBody() != null) {
            Gson gson = new Gson();
            try {
                String errorBody = execute.errorBody().string();
                MarvelError marvelError = gson.fromJson(errorBody, MarvelError.class);
                marvelCode = marvelError.getCode();
                marvelDescription = marvelError.getMessage();
                if (marvelDescription == null || "".equals(marvelDescription)) {
                    marvelDescription = marvelError.getStatus();
                }
            } catch (IOException e) {
            }
        }

        if(execute.code() == INVALID_AUTH_CODE) {
            throw new MarvelAuthApiException(execute.code(), marvelCode, marvelDescription, null);
        } else {
            throw new MarvelApiException(execute.code(), marvelCode, marvelDescription, null);
        }
    }
*/
}
