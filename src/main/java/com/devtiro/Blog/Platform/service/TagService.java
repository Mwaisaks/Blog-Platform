package com.devtiro.Blog.Platform.service;

import com.devtiro.Blog.Platform.domain.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> getTags();

    List<Tag> createTag(Set<String> tagNames);

    void deleteTag(UUID id);

    Tag getTagByid(UUID id);

    List<Tag> getTagByIds(Set<UUID> ids);
}
