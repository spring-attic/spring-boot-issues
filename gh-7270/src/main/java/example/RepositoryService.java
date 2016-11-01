package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {
  @Autowired GitHub gitHub;

  public String description(String owner, String repository) {
    return gitHub.getRepository(owner, repository).description;
  }
}
