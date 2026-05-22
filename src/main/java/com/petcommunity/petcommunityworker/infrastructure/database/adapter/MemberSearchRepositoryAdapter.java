package com.petcommunity.petcommunityworker.infrastructure.database.adapter;

import com.petcommunity.petcommunityworker.domain.member.MemberSearchRepository;
import com.petcommunity.petcommunityworker.domain.member.model.MemberSearch;
import com.petcommunity.petcommunityworker.infrastructure.database.elasticsearch.ElasticMemberSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberSearchRepositoryAdapter implements MemberSearchRepository {

    private final ElasticMemberSearchRepository repository;

    @Override
    public Optional<MemberSearch> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(MemberSearch document) {
        repository.save(document);
    }
}
