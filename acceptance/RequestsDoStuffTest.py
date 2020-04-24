import unittest

import requests

BASE_URL = "http://localhost:8080"


class RequestsDoStuff(unittest.TestCase):
    def test_head_request_to_get_user(self):
        self.assertEqual(requests.head(BASE_URL + "/api/user/1").status_code, 200)

    def test_option_request_to_get_user(self):
        self.assertEqual(requests.options(BASE_URL + "/api/user/1").status_code, 200)

    def test_option_request_to_create_user(self):
        self.assertEqual(requests.options(BASE_URL + "/api/user").status_code, 200)


if __name__ == '__main__':
    unittest.main()
