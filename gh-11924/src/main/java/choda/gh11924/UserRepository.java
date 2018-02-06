package choda.gh11924;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface UserRepository extends MongoRepository<User, String>,
                                        QueryDslPredicateExecutor<User> {}
