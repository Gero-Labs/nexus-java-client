package adlabs.nexus.client.backend.api.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {
    private Integer page;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
