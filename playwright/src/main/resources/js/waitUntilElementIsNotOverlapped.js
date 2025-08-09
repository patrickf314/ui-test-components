// Waits until an element is not overlapped by another element
// noinspection BadExpressionStatementJS
/**
 * Waits for the given node to not be overlapped by another element
 *
 * @param {HTMLElement} node the node
 * @param {{timeout: number}} arg the argument
 * @returns {Promise<void>} the promise resolving when the input is not loading
 */
(node, {timeout}) => new Promise((resolve, reject) => {
    let counter = timeout * 20;

    function checkEditable() {
        counter--;

        const rect = node.getBoundingClientRect();
        const x = rect.left + rect.width / 2;
        const y = rect.top + rect.height / 2

        if (x < 0 || x > window.innerWidth || y < 0 || y > window.innerHeight) {
            // Element is outside the viewport. Thus, we cannot check.
            resolve()
            return;
        }

        const elementAtPoint = document.elementFromPoint(x, y);

        if (node.contains(elementAtPoint) || elementAtPoint === node) {
            resolve();
        } else if (counter === 0) {
            reject(new Error(`Element is still overlapped after ${timeout} seconds. Overlapping element: ${elementAtPoint.outerHTML}`));
        } else {
            setTimeout(checkEditable, 50);
        }
    }

    checkEditable();
})