import { TestBed, inject } from '@angular/core/testing';

import { UserPrefPageService } from '../user-pref-page.service';

describe('UserPrefPageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserPrefPageService]
    });
  });

  it('should be created', inject([UserPrefPageService], (service: UserPrefPageService) => {
    expect(service).toBeTruthy();
  }));
});
