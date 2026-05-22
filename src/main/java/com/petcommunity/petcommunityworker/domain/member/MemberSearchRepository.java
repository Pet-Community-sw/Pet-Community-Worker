package com.petcommunity.petcommunityworker.domain.member;


import com.petcommunity.petcommunityworker.domain.member.model.MemberSearch;

import java.util.Optional;

public interface MemberSearchRepository {
    Optional<MemberSearch> find(Long id);

    void save(MemberSearch document);
}