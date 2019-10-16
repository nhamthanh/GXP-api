package com.example.demo.converter;

import com.example.demo.dto.PersonDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.PersonEntity;

import io.jenetics.jpx.Person;

public class PersonConverter {

    /**
     * Convert gpx author data to entity
     * 
     * @param author
     * @param gpxEntity
     */
    public static void gpxToEntity(Person author, GPXEntity gpxEntity) {
        // check null
        if (author == null) {
            return;
        }
        PersonEntity entity = new PersonEntity();
        entity.setName(author.getName().orElse(null));
        gpxEntity.setAuthor(entity);
    }

    /**
     * Convert author entity data to dto
     * 
     * @param entity
     * @param dto
     */
    public static void entityToDTO(PersonEntity entity, PersonDTO dto) {
        // check null
        if (entity == null) {
            return;
        }
        dto.setId(entity.getId());
        dto.setName(entity.getName());
    }
}
