
package acme.components;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select count(b) from CommercialBanner b")
	int countCommercialBanners();

	@Query("select count(n) from NonCommercialBanner n")
	int countNonCommercialBanners();

	@Query("select b from CommercialBanner b")
	List<Banner> findManyCommercialBanners(PageRequest pageRequest);

	@Query("select n from NonCommercialBanner n")
	List<Banner> findManyNonCommercialBanners(PageRequest pageRequest);

	default Banner findRandomBanner() {
		Banner result;
		int bannerCount, bannerIndex;
		ThreadLocalRandom random;
		PageRequest page;
		List<Banner> list;

		random = ThreadLocalRandom.current();
		bannerIndex = random.nextInt();

		if (bannerIndex % 2 == 0) {
			bannerCount = this.countCommercialBanners();
			random = ThreadLocalRandom.current();
			bannerIndex = random.nextInt(0, bannerCount);
			page = PageRequest.of(bannerIndex, 1);
			list = this.findManyCommercialBanners(page);
		} else {
			bannerCount = this.countNonCommercialBanners();
			random = ThreadLocalRandom.current();
			bannerIndex = random.nextInt(0, bannerCount);
			page = PageRequest.of(bannerIndex, 1);
			list = this.findManyNonCommercialBanners(page);
		}
		result = list.isEmpty() ? null : list.get(0);

		return result;

	}
}
