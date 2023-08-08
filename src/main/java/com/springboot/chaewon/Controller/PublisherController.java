package com.springboot.chaewon.Controller;


import com.springboot.chaewon.Entity.Publisher;
import com.springboot.chaewon.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class PublisherController {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @PostMapping("/api/check-publisher")
    public String checkPublisher(@RequestBody PublisherRequest request) {
        String publisherName = request.getPublisherValue();
        try {
            // Mariadb에서 언론사 이름으로 조회
            Publisher publisher = publisherRepository.findByName(publisherName);

            // 언론사가 존재하는 경우 'Y'를 반환, 그렇지 않으면 'N'을 반환
            if (publisher != null) {
                return "Y";
            } else {
                return "N";
            }
        } catch (Exception e) {
            return "언론사 조회에 실패했습니다";
        }

    }



        // 프론트엔드에서 보낸 JSON을 매핑하기 위한 클래스
        public static class PublisherRequest {
            private String publisherValue;

            public String getPublisherValue() {
                return publisherValue;
            }

            public void setPublisherValue(String publisherValue) {
                this.publisherValue = publisherValue;
            }
        }
    }



