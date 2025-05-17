package jp.co.yamaha_motor.gimac.tools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.yamaha_motor.gimac.tools.service.HelpPDFGetService;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

/**
 * PDFファイルをS3から取得するコントローラークラスです。
 */
@RestController
@Slf4j
@RequestMapping("/tools/pdf")
public class HelpPDFGetController {

    @Autowired
    private HelpPDFGetService pdfGetService;

    /**
     * PDFファイルをS3からダウンロードします。
     *
     * @param screenCode 画面コード
     * @return PDFファイルのInputStreamResource
     */
    @PostMapping("/getPdf")
    public ResponseEntity<?> getPdf(@RequestParam("screenCode") String screenCode) {
        ResponseInputStream<GetObjectResponse> responseInputStream = pdfGetService.downloadFileFromS3(screenCode);
        if (responseInputStream != null) {
            try {
                return ResponseEntity.ok()
                        .header("Content-Type", "application/pdf")
                        .header("Content-Disposition", "attachment; filename=\"" + screenCode + "\"")
                        .body(new InputStreamResource(responseInputStream));
            } catch (Exception e) {
                log.error("Error while reading the file: " + e.getMessage());
            }
        }
        return ResponseEntity.status(500).body(null);

    }

}
