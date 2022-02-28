package com.api.dashboard.fixture;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.repository.NoticeRepository;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

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
                return Notice.builder().id(1L).build();
            }

            @Override
            public Notice update(Notice notice) {
                return notice;
            }

            @Override
            public boolean delete(Long noticeId) {
                return true;
            }


        };
    }
}
