package org.choongang.member.repositories;

import org.choongang.member.entities.Member;
import org.choongang.member.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
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
