package vn.edu.fpt.electricalconponents.apis;

import androidx.annotation.NonNull;

/**
 * Represents an error response from an API.
 * This class encapsulates the status and message of an error that occurred during an API request.
 * It provides a standardized way to communicate errors back to the client.
 */
public class ApiErrorResponse {
    private String status;
    private String message;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
