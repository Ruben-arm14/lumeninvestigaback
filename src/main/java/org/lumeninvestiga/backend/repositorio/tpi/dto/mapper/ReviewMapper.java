package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

<<<<<<< HEAD
import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper {

=======
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    ReviewResponse toReviewResponse(Review review);
>>>>>>> c56cb14e0fc6a8beb12fb9f7f9a7eb0f05581d94
}
