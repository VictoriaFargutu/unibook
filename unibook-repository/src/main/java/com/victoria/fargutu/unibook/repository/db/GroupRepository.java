package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
