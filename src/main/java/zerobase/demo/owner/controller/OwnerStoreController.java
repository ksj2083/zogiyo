package zerobase.demo.owner.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zerobase.demo.owner.dto.CreateStore;
import zerobase.demo.owner.dto.OpenCloseStore;
import zerobase.demo.owner.dto.StoreInfo;
import zerobase.demo.owner.dto.UpdateStore;
import zerobase.demo.owner.service.StoreService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/store")
public class OwnerStoreController {

	private final StoreService storeService;

	//가게 등록
	@PostMapping()
	public CreateStore.Response createStore(@RequestBody CreateStore.Request request,
		@AuthenticationPrincipal UserDetails loggedInUser) {

		CreateStore dto = CreateStore.fromRequest(request);
		if(loggedInUser != null) dto.setLoggedInUser(loggedInUser);

		return storeService.createStore(dto);
	}

	//가게 열기/닫기
	@PutMapping("/openclose")
	public OpenCloseStore.Response openCloseStore(@RequestBody OpenCloseStore.Request request,
		@AuthenticationPrincipal UserDetails loggedInUser) {

		OpenCloseStore dto = OpenCloseStore.fromRequest(request);
		if(loggedInUser != null) dto.setLoggedInUser(loggedInUser);

		return storeService.openCloseStore(OpenCloseStore.fromRequest(request));
	}

	//보유 가게 조회
	@GetMapping()
	public StoreInfo.Response getStoreByOwnerId(@RequestParam String ownerId) {

		return storeService.getStoreInfoByOwnerId(ownerId);
	}

	//점포 수정
	@PutMapping()
	public UpdateStore.Response updateStore(@RequestBody UpdateStore.Request request,
		@AuthenticationPrincipal UserDetails loggedInUser) {

		UpdateStore dto = UpdateStore.fromRequest(request);
		if(loggedInUser != null) dto.setLoggedInUser(loggedInUser);

		return storeService.updateStore(UpdateStore.fromRequest(request));
	}
}
