package sample.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import sample.api.RestInvokerSupport;
import sample.context.AppSetting;
import sample.context.AppSetting.FindAppSetting;
import sample.context.audit.*;
import sample.context.audit.AuditActor.FindAuditActor;
import sample.context.audit.AuditEvent.FindAuditEvent;
import sample.context.orm.PagingList;

/**
 * システム系社内ユースケースの API 実行処理を表現します。
 */
@Component
public class SystemAdminFacadeInvoker extends RestInvokerSupport implements SystemAdminFacade {

    @Value("${extension.remoting.app}")
    String applicationName;
    
    /** {@inheritDoc} */
    @Override
    public String applicationName() {
        return applicationName;
    }
    
    /** {@inheritDoc} */
    @Override
    public String rootPath() {
        return Path;
    }

    /** {@inheritDoc} */
    @Override
    public PagingList<AuditActor> findAuditActor(FindAuditActor p) {
        return invoker().get(PathFindAudiActor, new ParameterizedTypeReference<PagingList<AuditActor>>() {}, p);
    }

    /** {@inheritDoc} */
    @Override
    public PagingList<AuditEvent> findAuditEvent(FindAuditEvent p) {
        return invoker().get(PathFindAudiEvent, new ParameterizedTypeReference<PagingList<AuditEvent>>() {}, p);
    }

    /** {@inheritDoc} */
    @Override
    public List<AppSetting> findAppSetting(FindAppSetting p) {
        return invoker().get(PathFindAppSetting, new ParameterizedTypeReference<List<AppSetting>>() {}, p);
    }

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> changeAppSetting(String id, String value) {
        return invoker().postEntity(PathChangeAppSetting, Void.class, null, id, value);
    }

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> processDay() {
        return invoker().postEntity(PathProcessDay, Void.class, null);
    }
    
}
