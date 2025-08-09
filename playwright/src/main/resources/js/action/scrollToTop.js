// Scrolls to the top inside the closed y-scrollable element.
// noinspection BadExpressionStatementJS
/**
 * Scrolls the top of the closed y-scrollable element.
 * If no scrollable element is found, then the window is scrolled to the top.
 *
 * @param {HTMLElement} node the element to scroll in
 * @returns {Promise<string>} the promise resolving to the full value of the matching option
 */
    (node) => {

    /**
     * @param {HTMLElement} element
     */
    function scrollToTop(element) {
        if (element.scrollHeight > element.clientHeight) {
            element.scrollTop = 0;
        } else if (element.parentElement !== null) {
            scrollToTop(element.parentElement);
        } else {
            window.scrollTo({top: 0});
        }
    }

    scrollToTop(node);
}