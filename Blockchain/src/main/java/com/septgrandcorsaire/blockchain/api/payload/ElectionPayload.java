package com.septgrandcorsaire.blockchain.api.payload;

import com.septgrandcorsaire.blockchain.api.error.exception.ErrorCode;
import com.septgrandcorsaire.blockchain.api.error.exception.IllegalPayloadArgumentException;
import com.septgrandcorsaire.blockchain.application.ElectionQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.septgrandcorsaire.blockchain.infrastructure.util.DateTimeParser.parseDateTime;

/**
 * @author Nidhal TEYEB
 * @since 0.0.1-SNAPSHOT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElectionPayload {

    private List<String> candidates;

    private String startingDate;

    private String closingDate;

    private String electionName;

    private String countBlankVotes;

    private String status;

    public ElectionQuery toQuery() {
        validatePayload();
        return ElectionQuery.builder()
                .candidates(this.candidates)
                .startingDate(parseStartingDate())
                .closingDate(parseClosingDate())
                .electionName(this.electionName)
                .blankVotesCounted(Boolean.parseBoolean(countBlankVotes))
                .electionStatus(this.status)
                .build();
    }

    private LocalDateTime parseStartingDate() {
        return parseDateTime(this.startingDate, "starting_date");
    }

    private LocalDateTime parseClosingDate() {
        return parseDateTime(this.closingDate, "closing_date");
    }

    private void validatePayload() {
        if (electionName == null || electionName.isBlank()) {
            throw IllegalPayloadArgumentException.ofErrorCode(ErrorCode.REQUIRED_PARAMETER, "election_name");
        }
        if (startingDate == null || startingDate.isBlank()) {
            throw IllegalPayloadArgumentException.ofErrorCode(ErrorCode.REQUIRED_PARAMETER, "starting_date");
        }
        if (closingDate == null || closingDate.isBlank()) {
            throw IllegalPayloadArgumentException.ofErrorCode(ErrorCode.REQUIRED_PARAMETER, "closing_date");
        }
        if (candidates == null || candidates.isEmpty()) {
            throw IllegalPayloadArgumentException.ofErrorCode(ErrorCode.REQUIRED_PARAMETER, "candidates");
        }
        if (countBlankVotes != null) {
            if (!countBlankVotes.isBlank() && !("true".equals(countBlankVotes.toLowerCase()) || "false".equals(countBlankVotes.toLowerCase()))) {
                throw IllegalPayloadArgumentException.ofErrorCode(ErrorCode.INVALID_BOOLEAN_FORMAT, "count_blank_votes");
            }
        }

    }
}
