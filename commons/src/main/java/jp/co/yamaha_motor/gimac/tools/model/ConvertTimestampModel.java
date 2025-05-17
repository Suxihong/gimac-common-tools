package jp.co.yamaha_motor.gimac.tools.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The action for ConvertTimestamp model.
 * Merge Dhys0001SpModel and Dhys0002SpModel
 * @author VP01365
 *
 */
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
public class ConvertTimestampModel extends BaseSpModel {
    private Timestamp convertTimestamp;
}
