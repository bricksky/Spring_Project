package com.example.membershipmanagement.repository;

import com.example.membershipmanagement.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("donghyun");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(result, member);
       Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("donghyun1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("donghyun2");
        repository.save(member2);

       Member result = repository.findByName("donghyun1").get();
       Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("donghyun1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("donghyun2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}