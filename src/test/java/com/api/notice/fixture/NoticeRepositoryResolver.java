package com.api.notice.fixture;

import com.api.notice.domain.Notice;
import com.api.notice.repository.notice.NoticeRepository;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.time.LocalDate;

public class NoticeRepositoryResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == NoticeRepository.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new NoticeRepository() {
            @Override
            public Notice save(Notice notice) {
                return Notice.builder()
                        .id(1L)
                        .build();
            }

            @Override
            public Notice update(Notice notice) {
                return notice;
            }

            @Override
            public boolean deleteById(Long noticeId) {
                return true;
            }

            @Override
            public void viewNotice(Long noticeId) {

            }

            @Override
            public Notice findById(Long noticeId) {
                if (noticeId.equals(1L)){
                    return Notice.builder()
                            .id(1L)
                            .title("공지사항 제목 1")
                            .content("공지사항 내용 1")
                            .startDate(LocalDate.now())
                            .endDate(LocalDate.now().plusWeeks(1))
                            .build();
                }else{
                    return Notice.builder()
                            .id(2L)
                            .title("조회 기간 만료")
                            .content("공지사항 내용 1")
                            .startDate(LocalDate.now().minusWeeks(1))
                            .endDate(LocalDate.now().minusDays(1))
                            .build();
                }
            }


        };
    }
}
