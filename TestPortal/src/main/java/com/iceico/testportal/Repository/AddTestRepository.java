package com.iceico.testportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iceico.testportal.Model.AddTest;

/**
 * @author puja
 *
 */
@Repository
public interface AddTestRepository extends JpaRepository<AddTest, Long> {

}
