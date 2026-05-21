package com.petcommunity.petcommunityworker.infrastructure.database.adapter;

import com.petcommunity.petcommunityworker.domain.member.MemberSearchRepository;
import com.petcommunity.petcommunityworker.domain.member.model.MemberSearch;
import com.petcommunity.petcommunityworker.infrastructure.database.elasticsearch.ElasticMemberSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
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

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
