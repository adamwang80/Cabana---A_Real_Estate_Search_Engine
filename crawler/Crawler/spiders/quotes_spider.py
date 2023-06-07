import scrapy
from scrapy.shell import inspect_response
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor
from collections import deque
import csv
import datetime


class QuotesSpider(CrawlSpider):
    name = "quotes"
    start_urls = [
            'https://www.cc.gatech.edu/people',
            'https://design.gatech.edu/people',
            'https://iac.gatech.edu/'
    ]
    start_time = datetime.datetime.now()
    rules = (
        Rule(LinkExtractor(allow = "www.cc.gatech.edu/people", restrict_css=".pagination.justify-content-center", unique = True) ),
        Rule(LinkExtractor(allow = "www.cc.gatech.edu/people",restrict_css=".views-view-grid.horizontal.cols-2.clearfix > div > div > div > div > div > div > h4 > a"), callback="parse_coc"),
        Rule(LinkExtractor(allow = "design.gatech.edu/people",restrict_css='.row.justify-content-center > div > article > div > div > div > a'), callback="parse_design"),
        Rule(LinkExtractor(allow = "iac.gatech.edu",restrict_css='#gt-menu-main-tray-6 > ul > li > a')),
        Rule(LinkExtractor(allow = "iac.gatech.edu",restrict_css='.content > ul > li > div > p > a'), callback="parse_iac"),
    )
    custom_settings = {
       'FEED_URI' : 'people.csv'
    }
    headers = ['Pages/Second', 'Ratio of #URL crawled / #URL to be crawled']
    with open('stats.csv', mode='a') as stats_file:
        stats_writer = csv.writer(stats_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        stats_writer.writerow(headers)

    def parse_coc(self, response):
        name = response.css('.page-title > span::text').get()
        position = response.css('.card-block > h6::text').get()
        email = response.css('.card-block__text > a::text').extract_first()
        if not email or "@" not in email:
            email = None
        scraped_info = {
            'name' : name,
            'position' : position,
            'email' : email,
            'department': 'COC'
            # 'research areas' : research_areas
        }
        yield scraped_info
        pages_crawled = self.crawler.stats.get_value('downloader/response_count')
        ratio = pages_crawled / self.crawler.stats.get_value('scheduler/enqueued')
        time_elapsed = (datetime.datetime.now() - self.start_time).total_seconds()
        pages_per_second = self.crawler.stats.get_value('downloader/request_count') / time_elapsed
        self.logger.info(f'Pages crawled: {pages_crawled}, Ratio: {ratio:.2f}')
        self.logger.info(f'Pages/second: {pages_per_second}')
        
        with open('stats.csv', mode='a') as stats_file:
            stats_writer = csv.writer(stats_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            stats_writer.writerow([pages_per_second, ratio])
            
    def parse_design(self, response):
        name = response.css('.text-center.abel > span::text').get()
        position = response.css('.col-md-8.py-md-4 > p::text' ).get()
        email = response.css('.h5 > div > div > a::text').extract_first()
        scraped_info = {
            'name' : name,
            'position' : position,
            'email' : email,
            'department':'COD'
        }
        yield scraped_info
        pages_crawled = self.crawler.stats.get_value('downloader/response_count')
        ratio = pages_crawled / self.crawler.stats.get_value('scheduler/enqueued')
        self.logger.info(f'Pages crawled: {pages_crawled}, Ratio: {ratio:.2f}')
        time_elapsed = (datetime.datetime.now() - self.start_time).total_seconds()
        pages_per_second = self.crawler.stats.get_value('downloader/request_count') / time_elapsed
        self.logger.info(f'Pages/second: {pages_per_second}')
        with open('stats.csv', mode='a') as stats_file:
            stats_writer = csv.writer(stats_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            stats_writer.writerow([pages_per_second, ratio])
        
    def parse_iac(self, response):
        name = response.css('.content > h1::text').get()
        position = response.css('.iacPersonTitle > strong::text' ).get()
        email = response.css('#iacPersonLine_email > a::text').get()
        scraped_info = {
            'name' : name,
            'position' : position,
            'email' : email,
            'department':'IAC'
        }
        yield scraped_info
        pages_crawled = self.crawler.stats.get_value('downloader/response_count')
        ratio = pages_crawled / self.crawler.stats.get_value('scheduler/enqueued')
        self.logger.info(f'Pages crawled: {pages_crawled}, Ratio: {ratio:.2f}')
        time_elapsed = (datetime.datetime.now() - self.start_time).total_seconds()
        pages_per_second = self.crawler.stats.get_value('downloader/request_count') / time_elapsed
        self.logger.info(f'Pages/second: {pages_per_second}')
        with open('stats.csv', mode='a') as stats_file:
            stats_writer = csv.writer(stats_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            stats_writer.writerow([pages_per_second, ratio])


    