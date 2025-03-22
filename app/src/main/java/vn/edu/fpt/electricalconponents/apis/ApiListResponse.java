package vn.edu.fpt.electricalconponents.apis;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a generic API response containing a list of data.
 * This class encapsulates the standard response structure for API endpoints
 * that return paginated or non-paginated lists of resources.
 *
 * @param <T> The type of data contained in the list.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiListResponse<T>{
    private String status;
    private String message;
    private int size;
    private int page;
    private int totalPage;
    private int totalSize;
    private List<T> data;
}
