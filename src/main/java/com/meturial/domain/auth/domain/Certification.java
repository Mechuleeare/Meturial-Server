package com.meturial.domain.auth.domain;

import com.meturial.domain.auth.domain.type.Certified;
import com.meturial.domain.auth.exception.EmailNotCertifiedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash
public class Certification implements Serializable {

    @Id
    @Indexed
    private String email;

    private String code;

    private Certified certified;

    @TimeToLive
    private Integer codeExp;

    public Certification updateCode(String code) {
        this.code = code;
        return this;
    }

    public Certification updateCertified(Certified certified) {
        this.certified = certified;
        return this;
    }

    public void checkIsCertified() {
        if (this.getCertified() != Certified.CERTIFIED) {
            throw EmailNotCertifiedException.EXCEPTION;
        }
    }
}
