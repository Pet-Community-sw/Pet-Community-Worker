package com.petcommunity.domain.member;


import com.petcommunity.domain.member.model.MemberSearch;

import java.util.Optional;

public interface MemberSearchRepository {
    Optional<MemberSearch> find(Long id);

    void save(MemberSearch document);

    void delete(Long id);
}