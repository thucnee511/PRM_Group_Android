package vn.edu.fpt.electricalconponents.apis;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiItemResponse<T> {
    private int status;
    private String message;
    private T data;
}
