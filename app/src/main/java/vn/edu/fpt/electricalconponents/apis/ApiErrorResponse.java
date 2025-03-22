package vn.edu.fpt.electricalconponents.apis;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an error response from an API.
 * This class encapsulates the status and message of an error that occurred during an API request.
 * It provides a standardized way to communicate errors back to the client.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiErrorResponse {
    private String status;
    private String message;
}
