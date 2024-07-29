package org.choongang.member.repositories;

import org.choongang.member.entities.Member;
import org.choongang.member.entities.QMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    // 옵셔녈 형태로 값을 받아준다
    @EntityGraph(attributePaths = "authorities") // 이 권한 처음부터 가져오도록 설정
    /**
     * This is a Java annotation that is used in Spring Data JPA
     * to specify the entity graph for a query.
     * The `attributePaths` parameter specifies the attributes of the entity that should be
     * included in the entity graph.
     * In this case, the `authorities` attribute of the `Member` entity will be included
     * in the entity graph. This can be useful for optimizing performance
     * when loading related entities.
     */
    Optional<Member> findByEmail(String email); // 이메일 가지고 회원 조회

    default boolean exists(String email) {
        // predicate의 매개변수인 exists를 통해 확인
        QMember qMember = QMember.member;
        return exists(qMember.email.eq(email)); // default가 있기 때문에 this는 생략 가능\
        /**
         * This Java code snippet is from a `MemberRepository` interface that extends `JpaRepository` and `QuerydslPredicateExecutor`. The `exists` method is a default method that checks if a member with a given email exists in the database.
         *
         * The `QMember qMember = QMember.member;` line creates a `QMember` object, which is a Querydsl query type for the `Member` entity. The `email` field of the `QMember` object is then used in the `eq` method to create a predicate that checks if the email is equal to the given email.
         *
         * The `exists` method then takes this predicate and checks if any member in the database matches the predicate. The `return` statement returns `true` if a member with the given email exists, and `false` otherwise.
         *
         * The comment `// default가 있기 때문에 this는 생략 가능` suggests that the `this` keyword is not needed because there is a default implementation of the `exists` method in the interface.
         */
    }
}
