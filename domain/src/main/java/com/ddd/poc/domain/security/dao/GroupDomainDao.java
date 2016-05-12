package com.ddd.poc.domain.security.dao;

import com.ddd.poc.domain.security.model.GroupDM;
import com.ddd.poc.domain.security.model.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupDomainDao {

    private final GroupEntityDao groupEntityDao;

    @Autowired
    public GroupDomainDao(GroupEntityDao groupEntityDao) {
        this.groupEntityDao = groupEntityDao;
    }

    public Optional<GroupDM> find(Long id) {
        GroupEntity groupEntity = groupEntityDao.findOne(id);
        return groupEntity != null ? Optional.of(new GroupDM(groupEntity, groupEntityDao)) : Optional.<GroupDM>empty();
    }

    public GroupDM create() {
        return new GroupDM(groupEntityDao);
    }
}
