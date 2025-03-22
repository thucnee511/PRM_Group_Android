package vn.edu.fpt.electricalconponents.apis;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Represents a generic API response containing a list of data.
 * This class encapsulates the standard response structure for API endpoints
 * that return paginated or non-paginated lists of resources.
 *
 * @param <T> The type of data contained in the list.
 */

public class ApiListResponse<T> {
    private String status;
    private String message;
    private int size;
    private int page;
    private int totalPage;
    private int totalSize;
    private List<T> data;

    public ApiListResponse() {
    }

    public ApiListResponse(String status, String message, int size, int page, int totalPage, int totalSize, List<T> data) {
        this.status = status;
        this.message = message;
        this.size = size;
        this.page = page;
        this.totalPage = totalPage;
        this.totalSize = totalSize;
        this.data = data;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiListResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", size=" + size +
                ", page=" + page +
                ", totalPage=" + totalPage +
                ", totalSize=" + totalSize +
                ", data=" + data +
                '}';
    }
}
