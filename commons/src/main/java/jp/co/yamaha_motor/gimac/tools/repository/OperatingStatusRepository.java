package jp.co.yamaha_motor.gimac.tools.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.yamaha_motor.gimac.tools.model.OperatingStatusModel;

@Repository
public interface OperatingStatusRepository {
    List<OperatingStatusModel> findAll();

    String updateOperatingStatus(OperatingStatusModel operatingStatusModel);
}
