package org.choongang.file.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.global.entities.BaseMemberEntity;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class FileInfo extends BaseMemberEntity {

    @Id @GeneratedValue
    private long seq; // 서버에 업로드 될 파일 이름 - ex) seq.확장자

    @Column(length = 45, nullable = false)
    private String gid = UUID.randomUUID().toString(); // 그룹 ID

    @Column(length = 45)
    private String name; // 파일 이름

    @Column(length = 80, nullable = false)
    private String location; // 그룹 안에 세부 위치

    @Column(length = 80, nullable = false)
    private String FileName;

    @Column(length = 80)
    private String contentType;

    private boolean done; // 그룹 작업 완료 여부


}
