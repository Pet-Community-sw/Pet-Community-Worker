package com.petcommunity.petcommunityworker.infrastructure.database.elasticsearch;

import com.petcommunity.petcommunityworker.domain.member.model.MemberSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticMemberSearchRepository extends ElasticsearchRepository<MemberSearch, Long> {
}
