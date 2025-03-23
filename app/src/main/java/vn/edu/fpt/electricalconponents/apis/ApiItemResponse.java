package vn.edu.fpt.electricalconponents.apis;


import androidx.annotation.NonNull;

/**
 * Represents a generic API response containing a status, message, and an optional data payload.
 *
 * <p>This class is designed to provide a standardized structure for API responses, allowing for
 * consistent communication of the success or failure of a request, along with a descriptive message
 * and the actual data returned by the API.</p>
 *
 * @param <T> The type of the data payload. This can be any Java object, allowing flexibility in the
 *            type of data returned by the API. If no data is returned, this can be set to Void or null.
 */
public class ApiItemResponse<T> {
    private int status;
    private String message;
    private T data;

    public ApiItemResponse() {
    }

    public ApiItemResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiItemResponse{" +
                "status='" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
